import create from 'zustand';

const useDetaulQuestion = create((set) => ({
  detailData: {},
  setDetailData: (value) => {
    set(() => ({ detailData: value }));
  },
  prevAnswerData: [],
  setPrevAnswerData: (value) => {
    set(() => ({ prevAnswerData: value }));
  },
}));

export default useDetaulQuestion;
