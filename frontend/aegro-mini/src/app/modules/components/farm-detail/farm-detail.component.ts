import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import { Location } from '@angular/common';

import {FieldInMemoryService} from "../../../core/services/field-in-memory.service";
import {Farm} from "../../../core/models/farm";
import {Field} from "../../../core/models/field";
import {FarmService} from "../../../core/services/farm.service";
import {FieldService} from "../../../core/services/field.service";

@Component({
  selector: 'app-farm-detail',
  templateUrl: './farm-detail.component.html',
  styleUrls: ['./farm-detail.component.css']
})
export class FarmDetailComponent implements OnInit {
  farm: Farm;
  fieldList: Field[] = [];
  id: string;

  constructor(
    private route: ActivatedRoute,
    private fieldService: FieldService,
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
    this.fieldService.getFieldsByFarmId(this.id)
      .subscribe(fields => this.fieldList = fields['data']);
  }

  goBack() {
    this.location.back();
  }
}
