import {Component, Input, OnInit} from '@angular/core';
import {FieldService} from "../../../core/services/field.service";
import {Field} from "../../../core/models/field";

@Component({
  selector: 'app-fields',
  templateUrl: './fields.component.html',
  styleUrls: ['./fields.component.css']
})
export class FieldsComponent implements OnInit {

  @Input() farmId: string;
  fields: Field[] = [];

  constructor(
    private fieldService: FieldService
  ) { }

  ngOnInit(): void {
    this.getFields();
  }

  getFields(): void {
    this.fieldService.getFieldsByFarmId(this.farmId)
      .subscribe(fields => this.fields = fields['data']);
  }

}
