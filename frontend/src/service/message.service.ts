import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Message} from '../model/message';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient) {
  }

  messages(): Observable<Message[]> {
    return this.http.get<Message[]>('/api/messages')
  }

  send(message: Message): Observable<void> {
    return this.http.post<void>('/api/messages', message)
  }
}
