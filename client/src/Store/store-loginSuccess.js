import create from 'zustand';

const useLoginSuccessStore = create((set) => ({
  loginSuccess: false,
  setLoginSuccess: (value) => {
    set(() => ({ loginSuccess: value }));
  },
}));

export default useLoginSuccessStore;
