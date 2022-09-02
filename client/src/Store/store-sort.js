import create from 'zustand';

const useSortStore = create((set) => ({
  sort: 'newest',
  pagination: 1,
  query: '',
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
}));
export default useSortStore;
