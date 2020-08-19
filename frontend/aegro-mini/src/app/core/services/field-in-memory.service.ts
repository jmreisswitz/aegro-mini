import { Injectable } from '@angular/core';
import {Field} from "../models/field";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FieldInMemoryService {
  private fields: Field[];

  constructor() {
    this.fields = [
      {id: '123456', area: 123, farmId: 'abcd', name: 'Field 1'},
      {id: '654321', area: 321, farmId: 'abcd', name: 'Field 2'},
      {id: 'dddd', area: 5, farmId: 'abcde', name: 'Field 3'},
      {id: 'eeeee', area: 8, farmId: 'abcde', name: 'Field 4'}
    ]
  }

  getField(id: string): Observable<Field> {
    return of(this.fields.find(field => field.id === id));
  }

  getFieldsByFarmId(farmId: string): Field[] {
    return this.fields.filter(field => field.farmId == farmId);
  }
}
