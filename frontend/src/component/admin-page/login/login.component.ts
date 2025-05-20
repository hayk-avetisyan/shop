import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../../service/auth.service';

@Component({
  standalone: false,
  selector: 'shop-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  usernameError: string = '';
  passwordError: string = '';
  loginError: string = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {
  }

  onLogin(): void {
    // Reset error messages
    this.usernameError = '';
    this.passwordError = '';
    this.loginError = '';

    // Validate inputs
    let isValid = true;

    if (!this.username || this.username.trim() === '') {
      this.usernameError = 'Օգտանունը պարտադիր է';
      isValid = false;
    }

    if (!this.password || this.password.trim() === '') {
      this.passwordError = 'Գաղտնաբառը պարտադիր է';
      isValid = false;
    }

    if (!isValid) {
      return;
    }

    // Call auth service
    this.authService.login(this.username, this.password)
      .subscribe({
        next:() => this.router.navigate(['/admin']),
        error: () => this.loginError = 'Սխալ օգտանուն կամ գաղտնաբառ'
      });
  }
}
