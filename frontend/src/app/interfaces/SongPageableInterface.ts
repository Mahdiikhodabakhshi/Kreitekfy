export interface SongPageableInterface {
  content: Content[]
  pageable: Pageable
  last: boolean
  totalElements: number
  totalPages: number
  size: number
  number: number
  sort: Sort2
  first: boolean
  numberOfElements: number
  empty: boolean
}

export interface Content {
  id: number
  title: string
  duration: number
  uploadDate: string
  totalViews: number
  totalRate: number
  albumId: number
  albumName: string
  albumImage: string
  albumImageType: string
  categoryId: number
  categoryName: string
  artists: Artist[]
}

export interface Artist {
  id: number
  name: string
}

export interface Pageable {
  pageNumber: number
  pageSize: number
  sort: Sort
  offset: number
  paged: boolean
  unpaged: boolean
}

export interface Sort {
  empty: boolean
  sorted: boolean
  unsorted: boolean
}

export interface Sort2 {
  empty: boolean
  sorted: boolean
  unsorted: boolean
}
