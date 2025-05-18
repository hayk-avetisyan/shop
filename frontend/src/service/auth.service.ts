import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, ReplaySubject, map, switchMap} from 'rxjs';
import {User} from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private subject = new ReplaySubject<User | undefined>(1);

  constructor(private http: HttpClient) {
    this.subject.next(undefined);
  }

  public csrf() {
    this.http.get('/api/session/csrf').subscribe()
  }

  public login(username: string, password: string): Observable<boolean> {
    return this.http.post<void>('/api/login?username=' + username + '&password=' + password, null).pipe(
      switchMap(() => {
        return this.http.get<User>('/api/session/me').pipe(
          map(user => {
              this.subject.next(user);
              return true;
            }
          ))
      })
    )
  }

  public loggedIn(): Observable<boolean> {
    return this.subject.asObservable().pipe(map(user => !!user))
  }
}
