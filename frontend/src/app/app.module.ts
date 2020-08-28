import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FarmsComponent } from './modules/components/farms/farms.component';
import { FarmDetailComponent } from './modules/components/farm-detail/farm-detail.component';
import { AppRoutingModule } from './modules/app-routing.module';
import { HttpClientModule} from "@angular/common/http";
import { FieldDetailComponent } from './modules/components/field-detail/field-detail.component';
import { FieldsComponent } from './modules/components/fields/fields.component';
import { ProductionsComponent } from './modules/components/productions/productions.component';
import { ProductionDetailComponent } from './modules/components/production-detail/production-detail.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    FarmsComponent,
    FarmDetailComponent,
    FieldDetailComponent,
    FieldsComponent,
    ProductionsComponent,
    ProductionDetailComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
