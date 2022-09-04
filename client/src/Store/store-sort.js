import create from 'zustand';

const useSortStore = create((set) => ({
  sort: 'newest',
  pagination: 1,
  query: '',
  data: [],
  pageInfo: {},
  setSort: (val) => {
    set(() => ({
      sort: val,
    }));
  },
  setPagination: (val) => {
    set(() => ({
      pagination: val,
    }));
  },
  setQuery: (val) => {
    set(() => ({
      query: val,
    }));
  },
  setData: (val) => {
    set(() => ({
      data: val,
    }));
  },
  setPageInfo: (val) => {
    set(() => ({
      pageInfo: val,
    }));
  },
}));
export default useSortStore;
