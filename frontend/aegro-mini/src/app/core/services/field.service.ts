import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Field} from "../models/field";
import {ErrorHandlingService} from "./error-handling.service";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class FieldService {
  private fieldUrl: string = 'http://localhost:8081/aegro_mini/field/'

  constructor(
    private http: HttpClient,
    private errorHandler: ErrorHandlingService
  ) { }

  getFieldsByFarmId(farmId: string): Observable<Field[]> {
    return this.http.get<Field[]>(this.fieldUrl + 'byfarm/' + farmId)
      .pipe(catchError(this.errorHandler.handleError<Field[]>('getFieldsByFarmId', [])));
  }

  addField(field: Field): Observable<Field> {
    return this.http.post<Field>(this.fieldUrl, field)
      .pipe(catchError(this.errorHandler.handleError<Field>('addField')));
  }

  getFieldById(fieldId: string): Observable<Field> {
    return this.http.get<Field>(this.fieldUrl + fieldId)
      .pipe(catchError(this.errorHandler.handleError<Field>('getFieldById')));
  }

  deleteField(fieldId: string): Observable<Field> {
    return this.http.delete<Field>(this.fieldUrl + fieldId)
      .pipe(catchError(this.errorHandler.handleError<Field>('deleteField')));
  }
}
