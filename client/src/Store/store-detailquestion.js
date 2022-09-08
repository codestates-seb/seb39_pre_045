import create from 'zustand';

const useDetailQuestion = create((set) => ({
  detailData: {},
  setDetailData: (value) => {
    set(() => ({ detailData: value }));
  },
  prevAnswerData: [],
  setPrevAnswerData: (value) => {
    set(() => ({ prevAnswerData: value }));
  },
}));

export default useDetailQuestion;
