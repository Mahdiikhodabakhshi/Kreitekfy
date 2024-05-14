
export interface SongInterface {
  id: number
  title: string
  duration: number
  uploadDate: string
  totalViews: number
  totalRate: number
  albumId: number
  albumTitle: string
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
