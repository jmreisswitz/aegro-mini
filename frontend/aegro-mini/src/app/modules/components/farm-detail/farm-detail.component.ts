import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import { Location } from '@angular/common';

import {Farm} from "../../../core/models/farm";
import {FarmService} from "../../../core/services/farm.service";
import {ProductivityService} from "../../../core/services/productivity.service";
import {Productivity} from "../../../core/models/productivity";

@Component({
  selector: 'app-farm-detail',
  templateUrl: './farm-detail.component.html',
  styleUrls: ['./farm-detail.component.css']
})
export class FarmDetailComponent implements OnInit {
  farm: Farm;
  productivityList: Productivity[] = []
  farmId: string;

  constructor(
    private route: ActivatedRoute,
    private farmService: FarmService,
    private productivityService: ProductivityService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.getFarm();
  }

  private getFarm() {
    this.farmId = this.route.snapshot.paramMap.get('id');
    this.farmService.getFarm(this.farmId)
      .subscribe(farm => this.farm = farm['data']);
  }

  public deleteFarm() {
    this.farmId = this.route.snapshot.paramMap.get('id');
    this.farmService.deleteFarm(this.farmId)
      .subscribe(farm => this.farm = farm['data']);
    this.goBack();
  }

  public calculateProductivity() {
    this.farmId = this.route.snapshot.paramMap.get('id');
    this.productivityService.getByFarmId(this.farmId)
      .subscribe(productivityList => this.productivityList = productivityList['data']);
  }

  goBack() {
    this.location.back();
  }
}
