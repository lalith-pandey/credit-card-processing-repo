export const luhnValidationHelper = (cardNumber: string): boolean => {
    if (!cardNumber.length) {
        return false;
    }
    // Removing all whitespaces using regex
    cardNumber = cardNumber.replace(/\s/g, '');
    console.log(cardNumber);

    // Step 1 - Save last digit in a constant
    const lastDigit = Number(cardNumber[cardNumber.length - 1]);

    // Step 2 - Reverse numbers on the card
    const reverseCardNumber = cardNumber.slice(0, cardNumber.length - 1)
        .split('').reverse().map(x => Number(x));

    let sum = 0;
    // Step 3 -  Multiply by 2 every digit on odd position. 
    // Step 4 - Subtract 9 if digit > 9
    for (let i = 0; i <= reverseCardNumber.length - 1; i += 2) {
        reverseCardNumber[i] = reverseCardNumber[i] * 2;
        if (reverseCardNumber[i] > 9) {
            reverseCardNumber[i] = reverseCardNumber[i] - 9;
        }
    }
    // 5. Add the numbers from Step4 above
    sum = reverseCardNumber.reduce((acc, currValue) => (acc + currValue), 0);
    // 6. Add last digit of card to sum from step 5 and calculate modulo 10. 
    // return true if module of 10 is 0
    
    return ((sum + lastDigit) % 10 === 0);
}