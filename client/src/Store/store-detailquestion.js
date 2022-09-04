import create from 'zustand';

const useDetaulQuestion = create((set) => ({
  detailData: {},
  setDetailData: (value) => {
    set(() => ({ detailData: value }));
  },
}));

export default useDetaulQuestion;
