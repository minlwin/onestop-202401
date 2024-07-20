import { computed, Injectable, signal } from "@angular/core";
import { DepartmentClientService } from "../../../services/client/department-client.service";

@Injectable()
export class DepartmentState {

  id = signal<number | undefined>(undefined)
  details = signal<any>(undefined)
  title = computed(() => this.details() ? `${this.details()?.code} : ${this.details()?.name}` : `No Data`)
  head = computed(() => this.details()?.head)
  doctors = computed(() => this.details()?.doctors || [])
  staffs = computed(() => this.details()?.staffs || [])

  saveSuccess = signal<boolean>(false)

  constructor(private client:DepartmentClientService) {}

  load(id:any) {
    this.id.set(id)
    this.client.findById(id).subscribe(result => this.details.set(result))
  }

  save(form:any) {
    const request = this.id() != undefined ? this.client.update(this.id()!, form) : this.client.create(form)
    request.subscribe(result => {
      this.details.set(result)
      this.saveSuccess.set(true)
    })
  }
}
