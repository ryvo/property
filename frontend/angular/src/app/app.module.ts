import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from "@angular/common/http";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';
import { AppComponent } from './app.component';
import { BreadcrumbComponent } from './components/breadcrumb/breadcrumb.component';
import { PortfolioComponent } from './components/portfolio/portfolio.component';
import { ApiService } from './services/api.service';
import { BuildingService } from "./services/building.service";

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
import { LoaderComponent } from './components/loader-component/loader.component';
import { LoaderService } from "./services/loader.service";
import { HttpService } from "./services/http.service";
import { HttpHandler } from "@angular/common/http";
import { httpServiceFactory } from "./factories/http-service.factory";

// Relates to FontAwesome
library.add(fas, far);

@NgModule({
  declarations: [
    AppComponent,
    BreadcrumbComponent,
    PortfolioComponent,
    LoaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    FontAwesomeModule,
    BrowserAnimationsModule,
    ButtonModule,
    DialogModule,
    InputTextModule,
    FieldsetModule,
    PanelModule,
    CardModule,
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
    MessageService,
    ApiService,
    BuildingService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
