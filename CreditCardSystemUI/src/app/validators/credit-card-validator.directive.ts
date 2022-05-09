import { NG_VALIDATORS, Validator, AbstractControl } from '@angular/forms';
import { luhnValidationHelper } from '../helpers/luhn-validation.helper';
import { Directive } from '@angular/core';

@Directive({
    selector: '[appCreditCardValidator]',
    providers: [{
        provide: NG_VALIDATORS,
        useExisting: CreditCardValidatorDirective,
        multi: true
    }]
})
export class CreditCardValidatorDirective implements Validator {
    validate(c: AbstractControl): { [key: string]: any } | null {
        if (null == c.value || isNaN(c.value)) {
            return { 'cardInvalid': true }
        } else if (!luhnValidationHelper(c.value)) {
            return { 'cardInvalid': true }
        }
        return null;
    }
}