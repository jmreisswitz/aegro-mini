import { Component, OnInit } from '@angular/core';
import {Production} from "../../../core/models/production";
import {ActivatedRoute} from "@angular/router";
import {ProductionService} from "../../../core/services/production.service";
import { Location } from '@angular/common';

@Component({
  selector: 'app-production-detail',
  templateUrl: './production-detail.component.html',
  styleUrls: ['./production-detail.component.css']
})
export class ProductionDetailComponent implements OnInit {

  production: Production;
  productionId: string;

  constructor(
    private route: ActivatedRoute,
    private productionService: ProductionService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.productionId = this.route.snapshot.paramMap.get('id');
    this.getProduction();
  }

  getProduction() {
    this.productionService.getProductionById(this.productionId)
      .subscribe(production => this.production = production['data']);
  }

  deleteProduction(): void {
    this.productionService.deleteProduction(this.productionId)
      .subscribe();
    this.goBack();
  }

  goBack() {
    this.location.back();
  }
}
