import {inject} from '@angular/core';
import {Router, UrlTree} from '@angular/router';
import {AuthService} from './service/auth.service';
import {map, Observable} from 'rxjs';

export function canActivateAdminPage(): Observable<boolean | UrlTree> {

  const authService = inject(AuthService);
  const router = inject(Router);

  return authService.loggedIn().pipe(map(loggedIn => loggedIn ? true : router.createUrlTree(['/admin/login'])));
}

export function canActivateLoginPage(): Observable<boolean | UrlTree> {

  const authService = inject(AuthService);
  const router = inject(Router);

  return authService.loggedIn().pipe(map(loggedIn => loggedIn ? router.createUrlTree(['/admin']) : true));
}
