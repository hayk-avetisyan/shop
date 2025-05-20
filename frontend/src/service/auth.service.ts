import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {catchError, map, Observable, of, ReplaySubject, switchMap, tap} from 'rxjs';
import {User} from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private subject = new ReplaySubject<User | undefined>(1);

  constructor(private http: HttpClient) {
  }

  public init() {
    this.http.get('/api/session/csrf').pipe(switchMap(() => this.loadUser())).subscribe()
  }

  public login(username: string, password: string): Observable<boolean> {
    return this.http.post<void>('/api/login?username=' + username + '&password=' + password, null).pipe(
      switchMap(() => this.loadUser()),
      map(() => true)
    )
  }

  private loadUser(): Observable<User> {
    return this.http.get<User>('/api/session/me').pipe(
      tap(user => this.subject.next(user)),
      catchError(() => this.next(undefined, false))
    );
  }

  public loggedIn(): Observable<boolean> {
    return this.subject.asObservable().pipe(map(user => !!user));
  }

  public logout(): Observable<boolean> {
    return this.http.post<void>('/api/logout', null).pipe(
      map(() => {
        this.subject.next(undefined);
        return true;
      })
    );
  }

  private next(user: User | undefined, data: any): Observable<any> {
    this.subject.next(user);
    return of(data);
  }
}
