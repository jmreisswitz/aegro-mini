import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FarmsComponent} from "./components/farms/farms.component";
import {FarmDetailComponent} from "./components/farm-detail/farm-detail.component";
import {FieldDetailComponent} from "./components/field-detail/field-detail.component";


const routes: Routes = [
  // { path: '', redirectTo: 'farms', pathMatch: 'full'},
  { path: '', component: FarmsComponent},
  { path: 'farms', component: FarmsComponent},
  { path: 'farm/:id', component: FarmDetailComponent },
  { path: 'field/:id', component: FieldDetailComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
