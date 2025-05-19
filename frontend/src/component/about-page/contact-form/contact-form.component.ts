import {Component} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Message} from '../../../model/message';
import {MessageService} from '../../../service/message.service';

@Component({
  standalone: false,
  selector: 'shop-contract-form',
  templateUrl: './contact-form.component.html',
  styleUrl: './contact-form.component.scss'
})
export class ContactFormComponent {

  public message: Message = this.empty()

  constructor(
    private snackBar: MatSnackBar,
    private messageService: MessageService
  ) {
  }

  send() {
    this.messageService.send(this.message).subscribe(
      () => {
        this.message = this.empty()
        this.snackBar.open('Ջեր նամակը ուղարկված է:', 'Լավ')
      },
      () => {
        this.snackBar.open('Առաջացել է խնդիր։ Խնդրում ենք փորձել մի փոքր ուշ կամ կապվել մեզ հետ։', 'Լավ')
      }
    )
  }

  private empty(): Message {
    return {
      id: -1,
      name: '',
      email: '',
      message: '',
      seen: false
    } as Message
  }
}
