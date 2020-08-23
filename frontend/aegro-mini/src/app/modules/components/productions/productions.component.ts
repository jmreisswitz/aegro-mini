import { Component, OnInit } from '@angular/core';
import {ProductionService} from "../../../core/services/production.service";
import {Production} from "../../../core/models/production";
import {Observer} from "rxjs";

@Component({
  selector: 'app-productions',
  templateUrl: './productions.component.html',
  styleUrls: ['./productions.component.css']
})
export class ProductionsComponent implements OnInit {

  productions: Production[];

  constructor(private productionService: ProductionService) { }

  ngOnInit(): void {
  }

  getProductions(fieldId: string): void{
    this.productionService.getProductionsByFieldId(fieldId)
      .subscribe(productions => this.productions = productions);
  }



}
