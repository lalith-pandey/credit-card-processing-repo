import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

import { CreditCard } from '../beans/credit-card';
import { CreditCardService } from '../services/credit-card.service';
import { getValidationConfigFromCardNo } from '../helpers/card.helper';
import { AbstractControl, FormGroup, Validators, FormControl } from '@angular/forms';


@Component({
  selector: 'app-credit-card',
  templateUrl: './credit-card.component.html',
  styleUrls: ['./credit-card.component.css']
})
export class CreditCardComponent implements OnInit {
  cardMask = [
    /\d/,
    /\d/,
    /\d/,
    /\d/,
    '-',
    /\d/,
    /\d/,
    /\d/,
    /\d/,
    '-',
    /\d/,
    /\d/,
    /\d/,
    /\d/,
    '-',
    /\d/,
    /\d/,
    /\d/,
    /\d/];

  creditCards: Observable<CreditCard[]> | undefined;

  creditCard: CreditCard = new CreditCard();
  submitted = false;
  message: String | undefined;
  errorMessage: String | undefined;
  cardNumberGroup!: FormGroup;

  constructor(private creditCardService: CreditCardService) {
  }

  ngOnInit() {
    this.cardNumberGroup = new FormGroup({
      cardNumber: new FormControl('', [
        Validators.required
      ])
    })
    this.creditCards = this.reloadData();
    console.log(this.creditCards);
  }

  reloadData(): Observable<CreditCard[]> {
    return this.creditCardService.getCreditCardList();
  }

  save() {
    this.creditCardService
      .addCreditCard(this.creditCard).subscribe((data: any) => {
        this.creditCard = new CreditCard();
        this.submitted = true;
        this.message = data.message;
        this.creditCards = this.reloadData();
      },
        (error: any) => {
          this.errorMessage = error.error.errorList;
          this.creditCards = this.reloadData();
        });
  }

  onSubmit() {
    this.submitted = false;
    this.errorMessage = undefined;
    this.message = undefined;
    this.save();
  }

  cardMaskFunction(rawValue: string): Array<RegExp> {
    const card = getValidationConfigFromCardNo(rawValue);
    if (card) {
      return card.mask;
    }
    return [/\d/];
  }

  getCardNumberControl(): AbstractControl | null {
    return this.cardNumberGroup && this.cardNumberGroup.get('cardNumber');
  }

}
