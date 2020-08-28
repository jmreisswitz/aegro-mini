import { Component, OnInit } from '@angular/core';
import {FieldService} from "../../../core/services/field.service";
import {Field} from "../../../core/models/field";
import {ActivatedRoute} from "@angular/router";
import { Location } from '@angular/common';
import {ProductivityService} from "../../../core/services/productivity.service";
import {Productivity} from "../../../core/models/productivity";

@Component({
  selector: 'app-field-detail',
  templateUrl: './field-detail.component.html',
  styleUrls: ['./field-detail.component.css']
})
export class FieldDetailComponent implements OnInit {

  field: Field;
  productivity: Productivity[] = [];
  fieldId: string;

  constructor(
    private route: ActivatedRoute,
    private fieldService: FieldService,
    private productivityService: ProductivityService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.getField();
  }

  getField(): void {
    this.fieldId = this.route.snapshot.paramMap.get('id');
    this.fieldService.getFieldById(this.fieldId)
      .subscribe(field => this.field = field['data']);
  }

  deleteField(): void {
    this.fieldId = this.route.snapshot.paramMap.get('id');
    this.fieldService.deleteField(this.fieldId)
      .subscribe();
    this.goBack();
  }

  calculateProductivity(): void {
    this.fieldId = this.route.snapshot.paramMap.get('id');
    this.productivityService.getByFieldId(this.fieldId)
      .subscribe(productivity => this.productivity = productivity['data']);
  }

  goBack() {
    this.location.back();
  }

}
