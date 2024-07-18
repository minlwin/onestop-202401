export function formData(data:any) {

  const form = new FormData

  for (const key in data) {
    form.append(key, data[key])
  }

  return form
}

export interface PageInfo {
  contents:any[]
  page:number
  size:number
  count:number
}

export interface Pager {
  page:number
  size:number
  count:number
}
