import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Order, OrderItem} from '../../../model/order';
import {OrderService} from '../../../service/order.service';
import {CartItem} from '../../../model/cart-item';

@Component({
  standalone: false,
  selector: 'shop-payment-popup',
  templateUrl: './payment-popup.component.html',
  styleUrl: './payment-popup.component.scss'
})
export class PaymentPopupComponent {

  private readonly orderErrorMessage: string = 'Առաջացել է խնդիր։ Խնդրում ենք փորձել մի փոքր ուշ կամ կապվել մեզ հետ։';
  private readonly orderPlacedMessage: string = 'Ջեր պատվերը ընդունված է։ Սպասեք օպերատորի զանգին:';

  private readonly cvvRegex = /^\d{3}$/;
  private readonly cardNumberRegex = /^\d{16}$/;
  private readonly expiryDateRegex = /^(0[1-9]|1[0-2])\/\d{2}$/;
  private readonly cardHolderRegex = /^[A-Za-z\s]{3,}$/;
  private readonly phoneNumberRegex = /^0[1-9][0-9]{7}$/;

  public cardNumberMessage: string = '';
  public cardHolderMessage: string = '';
  public phoneNumberMessage: string = '';
  public expiryDateMessage: string = '';
  public cvvMessage: string = '';
  public valid: boolean = false;

  public cardNumber: any;
  public cardHolder: any;
  public phoneNumber: any;
  public expiryDate: any;
  public cvv: any;

  public price: number;
  public items: OrderItem[];

  constructor(
    private snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<PaymentPopupComponent>,
    private orderService: OrderService,
    @Inject(MAT_DIALOG_DATA) private cartItems: CartItem[]
  ) {

    this.price = cartItems.map(item => item.product.price * item.quantity)
      .reduce((f, s) => f + s, 0)

    this.items = this.cartItems.map(item => {
      return {productId: item.product.id, quantity: item.quantity}
    });
  }

  public pay() {
    if (this.checkValidity()) {

      const order: Order = {
        items: this.items,
        price: this.price,
        contact: {
          name: this.cardHolder,
          phone: this.phoneNumber
        }
      };

      this.orderService.placeOrder(order).subscribe(
        succeed => this.showMessageAndClose(succeed ? this.orderPlacedMessage : this.orderErrorMessage),
        (): void => this.showMessageAndClose(this.orderErrorMessage)
      )
    }
  }

  public close() {
    this.dialogRef.close()
  }

  changeCardNumber(data: any) {
    this.cardNumber = data;
    this.cardNumberMessage = '';
  }

  changePhoneNumber(data: any) {
    this.phoneNumber = data;
    this.phoneNumberMessage = '';
  }

  changeCardHolder(data: any) {
    this.cardHolder = data;
    this.cardHolderMessage = '';
  }

  changeExpiryDate(data: any) {
    this.expiryDate = data;
    this.expiryDateMessage = '';
  }

  changeCvv(data: any) {
    this.cvv = data;
    this.cvvMessage = '';
  }

  private showMessageAndClose(message: string) {
    this.snackBar.open(message, "Լավ")
    this.dialogRef.close(true)
  }

  private checkValidity(): boolean {
    this.checkCardNumber();
    this.checkPhoneNumber();
    this.checkCardHolder();
    this.checkExpiryDate();
    this.checkCvvNumber();
    this.valid = !this.cardHolderMessage && !this.cardNumberMessage && !this.expiryDateMessage && !this.cvvMessage
    return this.valid;
  }

  private checkCardHolder() {
    const input = this.checkInput(this.cardHolder)
    const matches = this.cardHolderRegex.test(input);
    this.cardHolderMessage = !matches ? 'Մուտքագրեք ճիշտ անուն և ազգանուն:' : '';
  }

  private checkPhoneNumber() {
    const input = this.checkInput(this.phoneNumber)
    const matches = this.phoneNumberRegex.test(input);
    this.phoneNumberMessage = !matches ? 'Մուտքագրեք ճիշտ հեռախոսահամար - 077123456:' : '';
  }

  private checkCardNumber() {
    const input = this.checkInput(this.cardNumber).replaceAll(/\s+/g, '')
    const matches = this.cardNumberRegex.test(input);
    this.cardNumberMessage = !matches ? 'Քարտի համարը պետք է լինի 16 թվանշան:' : '';
  }

  private checkExpiryDate() {
    const input = this.checkInput(this.expiryDate)
    const matches = this.expiryDateRegex.test(input);
    this.expiryDateMessage = !matches ? 'Մուտքագրեք ժամկետ՝ MM/YY ձևաչափով:' : '';
  }

  private checkCvvNumber() {
    const input = this.checkInput(this.cvv)
    const matches = this.cvvRegex.test(input);
    this.cvvMessage = !matches ? 'CVV-ն պետք է լինի 3 թվանշան:' : '';
  }

  private checkInput(input: any): string {
    return input && typeof input == "string" ? input.trim() : ''
  }
}
