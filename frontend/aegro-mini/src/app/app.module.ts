import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FarmsComponent } from './modules/components/farms/farms.component';
import { FarmDetailComponent } from './modules/components/farm-detail/farm-detail.component';
import { AppRoutingModule } from './modules/app-routing.module';
import { HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    FarmsComponent,
    FarmDetailComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
