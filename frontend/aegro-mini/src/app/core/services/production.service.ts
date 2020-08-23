import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Production} from "../models/production";

@Injectable({
  providedIn: 'root'
})
export class ProductionService {
  private productionUrl: string = 'http://localhost:8081/aegro_mini/production/';

  constructor(private http: HttpClient) { }

  getProductionsByFieldId(fieldId: string): Observable<Production[]> {
    return this.http.get<Production[]>(this.productionUrl + 'field_id/' + fieldId);
  }

  getProductionById(productionId: string): Observable<Production> {
    return this.http.get<Production>(this.productionUrl + productionId);
  }

  deleteProduction(productionId: string): Observable<Production> {
    return this.http.delete<Production>(this.productionUrl + productionId);
  }
}
