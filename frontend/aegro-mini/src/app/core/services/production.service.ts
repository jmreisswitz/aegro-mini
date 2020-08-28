import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Production} from "../models/production";
import {ErrorHandlingService} from "./error-handling.service";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ProductionService {
  private productionUrl: string = 'http://localhost:8081/aegro_mini/production/';

  constructor(
    private http: HttpClient,
    private errorHandler: ErrorHandlingService
  ) { }

  addProduction(production: Production): Observable<Production> {
    return this.http.post<Production>(this.productionUrl, production)
      .pipe(catchError(this.errorHandler.handleError<Production>('addProduction')));
  }

  getProductionsByFieldId(fieldId: string): Observable<Production[]> {
    return this.http.get<Production[]>(this.productionUrl + 'field_id/' + fieldId)
      .pipe(catchError(this.errorHandler.handleError<Production[]>('getProductionsByFieldId', [])));
  }

  getProductionById(productionId: string): Observable<Production> {
    return this.http.get<Production>(this.productionUrl + productionId)
      .pipe(catchError(this.errorHandler.handleError<Production>('getProductionById')));
  }

  deleteProduction(productionId: string): Observable<Production> {
    return this.http.delete<Production>(this.productionUrl + productionId)
      .pipe(catchError(this.errorHandler.handleError<Production>('deleteProduction')));
  }
}
