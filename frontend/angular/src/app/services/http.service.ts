import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { HttpHandler } from "@angular/common/http";
import { LoaderService } from "./loader.service";
import { catchError, shareReplay } from "rxjs/operators";
import { Observable } from "rxjs/internal/Observable";
import { throwError } from "rxjs/internal/observable/throwError";

@Injectable({
  providedIn: 'root'
})
export class HttpService extends HttpClient {

  constructor(handler: HttpHandler, private loader: LoaderService) {
    super(handler);
  }

  public sendRequest<T>(method: string, url: string, body?: any): Observable<T> {
    let options = {};

    if (typeof body != 'undefined') {
      options['body'] = body;
    }

    console.log("Loader show");
    this.showLoader();

    let observable: Observable<T> = this.request<T>(method, url, options).pipe(
      shareReplay(),
//      delay(50000),
      catchError((error: HttpErrorResponse) => {
        return throwError(error);
      })
    );

    observable.subscribe({
      error: (error) => {
        console.log("Error, loader hide");
        this.hideLoader();
      },
      complete: () => {
        console.log("Loader hide");
        this.hideLoader();
      }
    });

    return observable;
  }

  private showLoader(): void {
    this.loader.show();
  }
  private hideLoader(): void {
    this.loader.hide();
  }
}
