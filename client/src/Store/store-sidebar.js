import create from 'zustand';

const useSideBarStore = create((set) => ({
  leftSideBarHidden: false,
  setLeftSideBarHidden: (value) => {
    set(() => ({ leftSideBarHidden: value }));
  },
}));

export default useSideBarStore;
