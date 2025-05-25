import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  constructor(private http: HttpClient) {
  }

  uploadFile(file: File): Observable<string> {
    const formData = new FormData();
    formData.append('file', file);

    return this.http.post<{ [key:string]: string }>('/api/file/upload', formData).pipe(
        map(data => data['filename'])
    )
  }

  fileSize(filename: string): Observable<number> {
    return this.http.get<{size: number}>(`/api/file/size`, {params: {filename: filename}})
      .pipe(map(data => data.size));
  }
}
