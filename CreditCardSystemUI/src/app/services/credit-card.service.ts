import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreditCardService {
  private baseUrl = 'http://localhost:8080/CreditCardSystem/api/v1/home';
  private addUrl = 'http://localhost:8080/CreditCardSystem/api/v1/add';
  constructor(private http: HttpClient) { }

  getCreditCardList(): Observable<any> {
    return this.http.get(this.baseUrl);
  }

  addCreditCard(creditCard: Object): Observable<Object> {
    return this.http.post(this.addUrl, creditCard);
  }
}
