import { computed, signal } from "@angular/core";

export class ScheduleState {
  details = signal<any>(undefined)
  selectedSchedule = signal<any>(undefined)

  doctor = computed(() => this.details()?.doctor)
  schedules = computed(() => this.details()?.schedules || [])

  profile = computed(() => {
    return {
      name : this.doctor()?.name,
      image : this.doctor()?.profile,
      phone : this.doctor()?.phone,
      email: this.doctor()?.email
    }
  })
}
