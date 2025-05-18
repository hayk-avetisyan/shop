import {inject} from '@angular/core';
import {ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import {AuthService} from './service/auth.service';
import  {Observable} from 'rxjs';

export function canActivateAdminPage(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
  return inject(AuthService).loggedIn();
}
