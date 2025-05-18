import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../service/auth.service';

@Component({
  standalone: false,
  selector: 'shop-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {

  constructor(
    private authService: AuthService,
  ) {
  }

  ngOnInit(): void {
    this.authService.csrf()
  }
}
