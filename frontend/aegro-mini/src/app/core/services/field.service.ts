import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Field} from "../models/field";

@Injectable({
  providedIn: 'root'
})
export class FieldService {
  private fieldUrl: string = 'http://localhost:8081/aegro_mini/field/'

  constructor(private http: HttpClient) { }

  getFieldsByFarmId(farmId: string): Observable<Field[]> {
    return this.http.get<Field[]>(this.fieldUrl + '/byfarm/' + farmId);
  }
}
