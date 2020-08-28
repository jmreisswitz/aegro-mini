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
  newFieldArea: number = 0;
  newFieldName: string = '';

  constructor(
    private fieldService: FieldService
  ) { }

  ngOnInit(): void {
    this.getFields();
  }

  addField(name: string, area: number) {
    this.fieldService.addField({name: name, area: area, farmId: this.farmId} as Field)
      .subscribe(field => this.fields.push(field['data']));
  }

  getFields(): void {
    this.fieldService.getFieldsByFarmId(this.farmId)
      .subscribe(fields => this.fields = fields['data']);
  }

}
