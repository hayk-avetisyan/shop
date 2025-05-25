import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FileUploadService} from '../../../service/file-upload.service';
import {finalize} from 'rxjs/operators';

@Component({
  standalone: false,
  selector: 'shop-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrl: './file-upload.component.scss'
})
export class FileUploadComponent implements OnInit {

  private readonly fileSizePrefixes = ['bytes', 'KB', 'MB', 'GB', 'TB'];

  uploading = false;
  selectedFile: {
    name: string,
    size: number,
  } | null = null;
  uploadedFilePath: string | null = null;

  @Input()
  public label: string = ''

  @Input()
  public fileType: string = '*/*'

  @Input()
  file: string | undefined = undefined;

  @Output()
  public fileChange: EventEmitter<string | undefined> = new EventEmitter<string | undefined>();

  public isImage: boolean = false;

  constructor(private fileUploadService: FileUploadService) {
  }

  ngOnInit() {
    this.isImage = this.fileType.startsWith('image/');

    if (this.file) {
      this.fileUploadService.fileSize(this.file).subscribe(size => {

        if (!this.file) {
          return
        }

        this.uploadedFilePath = this.file;
        this.selectedFile = {name: this.file, size: size};
      })
    }
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.uploadFile(input.files[0]);
    }
  }

  uploadFile(file: File): void {
    if (!file) {
      return;
    }

    this.selectedFile = file;
    this.uploading = true;
    this.fileUploadService.uploadFile(file)
      .pipe(finalize(() => this.uploading = false))
      .subscribe(filePath => {
        this.uploadedFilePath = filePath;
        this.fileChange.next(filePath);
      });
  }

  clearSelection(): void {
    this.selectedFile = null;
    this.uploadedFilePath = null;
    this.uploading = false;
    this.fileChange.next(undefined)
  }


  fileSize(): string {
    if (!this.selectedFile) {
      return '';
    }

    let prefixIndex = 0;
    let size: number = this.selectedFile.size;
    while (size >= 1024 && prefixIndex < this.fileSizePrefixes.length - 1) {
      size /= 1024;
      prefixIndex++;
    }

    return size.toFixed(2) + ' ' + this.fileSizePrefixes[prefixIndex];
  }
}
