import {Component, Input, OnInit} from '@angular/core';
import {ProductionService} from "../../../core/services/production.service";
import {Production} from "../../../core/models/production";

@Component({
  selector: 'app-productions',
  templateUrl: './productions.component.html',
  styleUrls: ['./productions.component.css']
})
export class ProductionsComponent implements OnInit {

  @Input() fieldId: string;
  productions: Production[] = [];
  newProductionType: string = '';
  newProductionAmount: number = 0;

  constructor(private productionService: ProductionService) { }

  ngOnInit(): void {
    this.getProductions(this.fieldId);
  }

  addProduction(productionType: string, productionAmount: number) {
    this.productionService.addProduction({
      fieldId: this.fieldId,
      productionType: productionType,
      productionAmount: productionAmount
    } as Production).subscribe(production => this.productions.push(production['data']));
  }

  getProductions(fieldId: string): void{
    this.productionService.getProductionsByFieldId(fieldId)
      .subscribe(productions => this.productions = productions['data']);
  }



}
