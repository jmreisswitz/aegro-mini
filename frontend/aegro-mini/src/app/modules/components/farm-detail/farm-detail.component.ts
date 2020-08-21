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
  id: string;

  constructor(
    private route: ActivatedRoute,
    private farmService: FarmService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.getFarm();
  }

  private getFarm() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.farmService.getFarm(this.id)
      .subscribe(farm => this.farm = farm['data']);
  }

  public deleteFarm() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.farmService.deleteFarm(this.id)
      .subscribe(farm => this.farm = farm['data']);
    this.goBack();
  }

  goBack() {
    this.location.back();
  }
}
