import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import { Location } from '@angular/common';

import {FieldInMemoryService} from "../../../core/services/field-in-memory.service";
import {FarmInMemoryService} from "../../../core/services/farm-in-memory.service";
import {Farm} from "../../../core/models/farm";
import {Field} from "../../../core/models/field";

@Component({
  selector: 'app-farm-detail',
  templateUrl: './farm-detail.component.html',
  styleUrls: ['./farm-detail.component.css']
})
export class FarmDetailComponent implements OnInit {
  @Input() farm: Farm;
  fieldList: Field[];

  constructor(
    private route: ActivatedRoute,
    private fieldService: FieldInMemoryService,
    private farmService: FarmInMemoryService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.getFarm();
  }

  private getFarm() {
    const id = this.route.snapshot.paramMap.get('id');
    this.farmService.getFarm(id).subscribe(farm => this.farm = farm);
    this.fieldList = this.fieldService.getFieldsByFarmId(id);
  }

  goBack() {
    this.location.back();
  }
}
