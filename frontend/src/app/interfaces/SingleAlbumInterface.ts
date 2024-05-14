export interface SingleAlbumInterface {
  id: number
  title: string
  image: string
  imageType: string
  songs: Song[]
}

export interface Song {
  id: number
  title: string
  duration: number
  totalViews: any
  totalRate: any
  uploadDate: string
  categoryId: number
  categoryName: string
  artists: Artist[]
}

export interface Artist {
  id: number
  name: string
}
