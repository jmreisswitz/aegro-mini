import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Productivity} from "../models/productivity";
import {ErrorHandlingService} from "./error-handling.service";
import {catchError} from "rxjs/operators";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProductivityService {
  private productivityUrl: string = environment.apiUrl + 'aegro_mini/productivity/';

  constructor(
    private http: HttpClient,
    private errorHandler: ErrorHandlingService
  ) { }

  getByFieldId(fieldId: string) : Observable<Productivity[]> {
    return this.http.get<Productivity[]>(this.productivityUrl + 'field_id/' + fieldId )
      .pipe(catchError(this.errorHandler.handleError<Productivity[]>('getByFieldId', [])));
  }

  getByFarmId(farmId: string): Observable<Productivity[]> {
    return this.http.get<Productivity[]>(this.productivityUrl + 'farm_id/' + farmId)
      .pipe(catchError(this.errorHandler.handleError<Productivity[]>('getByFarmId', [])));
  }
}
