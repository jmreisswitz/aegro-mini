import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import { Location } from '@angular/common';

import {Farm} from "../../../core/models/farm";
import {FarmService} from "../../../core/services/farm.service";

@Component({
  selector: 'app-farm-detail',
  templateUrl: './farm-detail.component.html',
  styleUrls: ['./farm-detail.component.css']
})
export class FarmDetailComponent implements OnInit {
  farm: Farm;
  farmId: string;

  constructor(
    private route: ActivatedRoute,
    private farmService: FarmService,
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

  goBack() {
    this.location.back();
  }
}
