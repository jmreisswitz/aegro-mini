import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FarmsComponent} from "./components/farms/farms.component";
import {FarmDetailComponent} from "./components/farm-detail/farm-detail.component";


const routes: Routes = [
  { path: '', redirectTo: 'farms', pathMatch: 'full'},
  { path: 'farms', component: FarmsComponent},
  { path: 'farm/:id', component: FarmDetailComponent }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
