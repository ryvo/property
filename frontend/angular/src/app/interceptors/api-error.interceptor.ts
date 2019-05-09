import {Injectable} from "@angular/core";
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {catchError} from "rxjs/operators";
import {MessageService} from "primeng/api";
import {throwError} from "rxjs/internal/observable/throwError";

@Injectable()
export class ApiErrorInterceptor implements HttpInterceptor {

  constructor(private messageService: MessageService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Error summary',
          detail: 'Error detail'
        });
        return throwError(error);
      })
    );
  }
}
