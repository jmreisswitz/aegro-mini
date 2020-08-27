import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Productivity} from "../models/productivity";

@Injectable({
  providedIn: 'root'
})
export class ProductivityService {
  private productivityUrl: string = 'http://localhost:8081/aegro_mini/productivity/';

  constructor(private http: HttpClient) { }

  getByFieldId(fieldId: string) : Observable<Productivity[]> {
    return this.http.get<Productivity[]>(this.productivityUrl + 'field_id/' + fieldId );
  }

  getByFarmId(farmId: string): Observable<Productivity[]> {
    return this.http.get<Productivity[]>(this.productivityUrl + 'farm_id/' + farmId);
  }
}
