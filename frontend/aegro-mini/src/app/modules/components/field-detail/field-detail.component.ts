import { Component, OnInit } from '@angular/core';
import {FieldService} from "../../../core/services/field.service";
import {Field} from "../../../core/models/field";
import {ActivatedRoute} from "@angular/router";
import { Location } from '@angular/common';

@Component({
  selector: 'app-field-detail',
  templateUrl: './field-detail.component.html',
  styleUrls: ['./field-detail.component.css']
})
export class FieldDetailComponent implements OnInit {

  field: Field;

  constructor(
    private route: ActivatedRoute,
    private fieldService: FieldService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.getField();
  }

  getField(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.fieldService.getFieldById(id)
      .subscribe(field => this.field = field['data']);
  }

  deleteField(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.fieldService.deleteField(id)
      .subscribe();
    this.goBack();
  }

  goBack() {
    this.location.back();
  }

}
