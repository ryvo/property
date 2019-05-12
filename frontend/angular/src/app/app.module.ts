import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';
import { AppComponent } from './app.component';
import { BreadcrumbComponent } from './components/breadcrumb/breadcrumb.component';
import { BuildingListComponent } from './buildings/building-list/building-list.component';
import { ApiService } from './services/api.service';
import { BuildingsService } from "./buildings/buildings.service";

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { FieldsetModule } from 'primeng/fieldset';
import { PanelModule } from 'primeng/panel';
import { CardModule } from 'primeng/card';
import { MessageService } from "primeng/api";
import { ToastModule } from 'primeng/toast';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { DropdownModule } from 'primeng/dropdown';
import { LoaderComponent } from './components/loader/loader.component';
import { SpinnerModule } from 'primeng/spinner';
import { LoaderService } from "./services/loader.service";
import { HttpService } from "./services/http.service";
import { HttpHandler } from "@angular/common/http";
import { httpServiceFactory } from "./factories/http-service.factory";
import { ApiErrorInterceptor } from "./interceptors/api-error.interceptor";
import { BuildingDashboardComponent } from './buildings/building-dashboard/building-dashboard.component';
import { RouterModule } from "@angular/router";
import { routes } from "./routes";
import { OwnerEditorComponent } from './owners/owner-editor/owner-editor.component';
import { BuildingUnitEditorComponent } from './building-units/building-unit-editor/building-unit-editor.component';


// Relates to FontAwesome
library.add(fas, far);

@NgModule({
  declarations: [
    AppComponent,
    BreadcrumbComponent,
    BuildingListComponent,
    LoaderComponent,
    BuildingDashboardComponent,
    OwnerEditorComponent,
    BuildingUnitEditorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(
      routes,
      { enableTracing: true } // For debugging purposes
    ),
    FontAwesomeModule,
    BrowserAnimationsModule,
    ButtonModule,
    DialogModule,
    InputTextModule,
    FieldsetModule,
    PanelModule,
    CardModule,
    DropdownModule,
    SpinnerModule,
    ToastModule,
    ProgressSpinnerModule
  ],
  providers: [
    LoaderService,
    {
      provide: HttpService,
      useFactory: httpServiceFactory,
      deps: [HttpHandler, LoaderService]
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ApiErrorInterceptor,
      multi: true
    },
    MessageService,
    ApiService,
    BuildingsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }