import {Component, OnInit} from '@angular/core';
import {MessageService} from '../../../service/message.service';
import {Message} from '../../../model/message';
import {ActivatedRoute} from '@angular/router';

@Component({
  standalone: false,
  selector: 'shop-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.scss']
})
export class MessagesComponent implements OnInit {
  messages: Message[] = [];
  filteredMessages: Message[] = [];

  showRead: boolean = true;
  showUnread: boolean = true;

  constructor(
    private messageService: MessageService,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {

      this.showRead = params['read'] === 'true' || params['read'] === undefined;
      this.showUnread = params['unread'] !== 'false' || params['unread'] === undefined;

      if (this.messages.length > 0) {
        this.applyFilters();
      } else {
        this.loadMessages();
      }
    });
  }

  loadMessages(): void {
    this.messageService.messages().subscribe(messages => {
      // Sort messages by id
      this.messages = messages.sort((a, b) => a.id - b.id);
      this.applyFilters();
    });
  }

  applyFilters(): void {
    this.filteredMessages = this.messages.filter(message => {
      if (this.showRead && message.seen) {
        return true;
      }
      else if (this.showUnread && !message.seen) {
        return true;
      }
      return false;
    });
  }

  deleteMessage(message: Message): void {
    this.messageService.delete(message.id).subscribe(() => {
      // Remove the message from the array
      this.messages = this.messages.filter(m => m.id !== message.id);
      this.applyFilters();
    });
  }

  markAsRead(message: Message): void {
    this.messageService.markAsRead(message.id).subscribe(() => {
      // Update the message in the array
      const index = this.messages.findIndex(m => m.id === message.id);
      if (index !== -1) {
        this.messages[index].seen = true;
        this.applyFilters();
      }
    });
  }

  markAsUnread(message: Message): void {
    this.messageService.markAsUnread(message.id).subscribe(() => {
      // Update the message in the array
      const index = this.messages.findIndex(m => m.id === message.id);
      if (index !== -1) {
        this.messages[index].seen = false;
        this.applyFilters();
      }
    });
  }
}
