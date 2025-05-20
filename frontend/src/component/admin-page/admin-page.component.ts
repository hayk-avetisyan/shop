import {Component} from '@angular/core';
import {AuthService} from '../../service/auth.service';
import {Router} from '@angular/router';

@Component({
  standalone: false,
  selector: 'shop-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrl: './admin-page.component.scss'
})
export class AdminPageComponent {

  showOrdersDone: boolean = true;
  showOrdersInProgress: boolean = true;
  showMessagesRead: boolean = true;
  showMessagesUnread: boolean = true;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  logout(): void {
    this.authService.logout().subscribe(() => {
      this.router.navigate(['/']);
    });
  }

  navigateToMessages(showRead: boolean, showUnread: boolean) {

    this.showMessagesRead = showRead;
    this.showMessagesUnread = showUnread;

    this.router.navigate(
      ['/admin/messages'],
      {queryParams: {read: showRead, unread: showUnread}}
    )
  }

  navigateToOrders(showDone: boolean, showInProgress: boolean) {

    this.showOrdersDone = showDone;
    this.showOrdersInProgress = showInProgress;

    this.router.navigate(
      ['/admin/orders'],
      {queryParams: {done: showDone, inProgress: showInProgress}}
    )
  }
}
